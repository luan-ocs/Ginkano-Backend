package com.cavaleiros.ginkano.endpoint;

import com.cavaleiros.ginkano.adapter.ErrorResponseAdapter;
import com.cavaleiros.ginkano.core.domain.request.DonationRequest;
import com.cavaleiros.ginkano.core.domain.response.*;
import com.cavaleiros.ginkano.usecase.DonationUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/donation")
@RequiredArgsConstructor
public class DonationController {

    private final DonationUsecase donationUsecase;

    @GetMapping("/all")
    public ResponseEntity<BodyResponse> getDonationAll(
            @RequestHeader final String authorization,
            @RequestHeader final String token
    ){

        try{
            DonationAllResponse donationAllResponse = donationUsecase.allDonations(authorization, token);
            return ResponseEntity.ok(donationAllResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @GetMapping("/")
    public ResponseEntity<BodyResponse> getDonationById(
            @RequestHeader final String code,
            @RequestHeader final String authorization
    ){

        try{
            DonationResponse donationResponse = donationUsecase.selectDonation(code, authorization);
            return ResponseEntity.ok(donationResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<BodyResponse> getDonationCreate(
            @RequestBody final DonationRequest donationRequest,
            @RequestHeader final String authorization
    ){

        try{
            DonationResponse donationResponse =  donationUsecase.createDonation(donationRequest, authorization);
            return ResponseEntity.ok(donationResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<BodyResponse> getDonationEdit(
            @RequestBody final DonationRequest donationTokenRequest,
            @RequestHeader final String authorization
    ){
        try{
            DonationResponse donationResponse =  donationUsecase.updateDonation(donationTokenRequest, authorization);
            return ResponseEntity.ok(donationResponse);
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<BodyResponse> getDonationDelete(
            @RequestBody final DonationRequest donationTokenRequest,
            @RequestHeader final String authorization
    ){
        try{
            DonationResponse donationResponse =  donationUsecase.deleteDonation(donationTokenRequest, authorization);
            return ResponseEntity.ok(donationResponse);
        }catch (Exception e){
            log.info(e.getMessage());
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }
}
