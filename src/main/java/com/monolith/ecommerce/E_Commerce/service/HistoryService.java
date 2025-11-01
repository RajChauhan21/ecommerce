package com.monolith.ecommerce.E_Commerce.service;

import com.monolith.ecommerce.E_Commerce.DTO.HistoryDto;
import com.monolith.ecommerce.E_Commerce.entity.History;
import com.monolith.ecommerce.E_Commerce.entity.User;
import com.monolith.ecommerce.E_Commerce.repository.HistoryRepository;
import com.monolith.ecommerce.E_Commerce.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoryService {

    @Autowired
    private HistoryRepository repository;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> addHistory(List<HistoryDto> ListHistoryDto){

        for (HistoryDto historyDto :ListHistoryDto){
            Optional<User> user = userRepository.findById(historyDto.getUserId());
            History history = new History();
            history.setUser(user.get());
            history.setImage(historyDto.getImage());
            history.setCreatedAt(historyDto.getCreatedAt());
            history.setLowerImage(historyDto.getLowerImage());
            history.setUpperImage(historyDto.getUpperImage());
            history.setPrice(historyDto.getPrice());
            history.setQuantity(historyDto.getQuantity());
            history.setOrderId(historyDto.getOrderId());
            history.setPaymentId(historyDto.getPaymentId());
            history.setProductCategory(historyDto.getProductCategory());
            history.setTotalPrice(historyDto.getTotalPrice());
            history.setFinalAmount(historyDto.getFinalAmount());
            history.setShoesImage(historyDto.getShoesImage());
            history.setProductName(historyDto.getProductName());
            history.setStatus(historyDto.getStatus());
            history.setPaymentMethod(historyDto.getPaymentMethod());
            repository.save(history);
        }

        return new ResponseEntity<>("history saved successfully", HttpStatus.ACCEPTED);
    }

    public ResponseEntity<?> getHistory(int userId){
        Optional<User> user = userRepository.findById(userId);
        List<History> histories = user.get().getHistories();

        return new ResponseEntity<>(histories,HttpStatus.ACCEPTED);
    }
}
