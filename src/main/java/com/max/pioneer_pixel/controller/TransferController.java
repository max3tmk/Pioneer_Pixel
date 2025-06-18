package com.max.pioneer_pixel.controller;

import com.max.pioneer_pixel.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/API/transfer")
@RequiredArgsConstructor
public class TransferController {

    private final AccountService accountService;

    @PostMapping
    public ResponseEntity<String> transfer(@RequestParam Long fromUserId,
                                           @RequestParam Long toUserId,
                                           @RequestParam BigDecimal amount) {
        try {
            accountService.transfer(fromUserId, toUserId, amount);
            return ResponseEntity.ok("Transfer successful");
        } catch (SecurityException se) {
            return ResponseEntity.status(403).body(se.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().body("Unexpected error: " + ex.getMessage());
        }
    }
}
