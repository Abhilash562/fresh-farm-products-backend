package com.example.fresh_farm_products.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fresh_farm_products.DTO.AddressResponse;
import com.example.fresh_farm_products.DTO.OrderItemResponse;
import com.example.fresh_farm_products.DTO.OrderResponse;
import com.example.fresh_farm_products.DTO.PlaceOrderRequest;
import com.example.fresh_farm_products.Entity.CustomerAddress;
import com.example.fresh_farm_products.Entity.Order;
import com.example.fresh_farm_products.Entity.OrderItem;
import com.example.fresh_farm_products.Entity.OrderStatus;
import com.example.fresh_farm_products.Exception.ResourceNotFoundException;
import com.example.fresh_farm_products.Repository.CustomerAddressRepository;
import com.example.fresh_farm_products.Repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
    private CustomerAddressRepository addressRepository;

    public OrderResponse placeOrder(PlaceOrderRequest request) {

        CustomerAddress address = addressRepository
                .findById(request.getAddressId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Delivery address not found"
                        )
                );

        String orderId = "ORD-" +
                UUID.randomUUID()
                        .toString()
                        .substring(0, 8)
                        .toUpperCase();

        double totalAmount = request.getItems()
                .stream()
                .mapToDouble(item ->
                        item.getPrice() * item.getQuantity()
                )
                .sum();

        Order order = Order.builder()
                .orderId(orderId)
                .customerId(request.getCustomerId())
                .totalAmount(totalAmount)
                .paymentMethod(request.getPaymentMethod())
                .orderStatus(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        List<OrderItem> orderItems = request.getItems()
                .stream()
                .map(item ->
                        OrderItem.builder()
                                .productId(item.getProductId())
                                .quantity(item.getQuantity())
                                .price(item.getPrice())
                                .order(order)
                                .build()
                )
                .toList();

        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        return convertToResponse(savedOrder, address);
    }

    public OrderResponse getOrderDetails(String orderId) {

        Order order = orderRepository
                .findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found"
                        )
                );

        return convertToResponse(order, null);
    }

    public List<OrderResponse> getCustomerOrders(String customerId) {
        return orderRepository
                .findByCustomerIdOrderByCreatedAtDesc(customerId)
                .stream()
                .map(order ->
                        convertToResponse(order, null)
                )
                .toList();
    }

    private OrderResponse convertToResponse(
            Order order,
            CustomerAddress address
    ) {

        OrderResponse response = new OrderResponse();

        response.setOrderId(order.getOrderId());
        response.setCustomerId(order.getCustomerId());
        response.setTotalAmount(order.getTotalAmount());
        response.setPaymentMethod(order.getPaymentMethod());
        response.setOrderStatus(order.getOrderStatus());
        response.setCreatedAt(order.getCreatedAt());

        if (address != null) {

            AddressResponse addressResponse = new AddressResponse();

            addressResponse.setId(address.getId());
            addressResponse.setFullName(address.getFullName());
            addressResponse.setMobileNumber(address.getMobileNumber());
            addressResponse.setAddressLine(address.getAddressLine());
            addressResponse.setVillage(address.getVillage());
            addressResponse.setCity(address.getCity());
            addressResponse.setState(address.getState());
            addressResponse.setCountry(address.getCountry());
            addressResponse.setPincode(address.getPincode());
            addressResponse.setLandmark(address.getLandmark());

            response.setDeliveryAddress(addressResponse);
        }

        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(item -> {

                    OrderItemResponse itemResponse = new OrderItemResponse();

                    itemResponse.setProductId(item.getProductId());
                    itemResponse.setQuantity(item.getQuantity());
                    itemResponse.setPrice(item.getPrice());
                    itemResponse.setTotal(
                            item.getPrice() * item.getQuantity()
                    );

                    return itemResponse;
                })
                .toList();

        response.setItems(items);

        return response;
    }
}
