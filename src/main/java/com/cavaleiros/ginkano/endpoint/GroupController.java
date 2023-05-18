package com.cavaleiros.ginkano.endpoint;

import com.cavaleiros.ginkano.adapter.ErrorResponseAdapter;
import com.cavaleiros.ginkano.core.domain.constants.Functions;
import com.cavaleiros.ginkano.core.domain.request.GroupRequest;
import com.cavaleiros.ginkano.core.domain.response.BodyResponse;
import com.cavaleiros.ginkano.core.domain.response.GroupAllResponse;
import com.cavaleiros.ginkano.core.domain.response.GroupResponse;
import com.cavaleiros.ginkano.usecase.GroupUsecase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/group")
public class GroupController {

    private final GroupUsecase groupUsecase;

    @GetMapping("/all")
    public ResponseEntity<BodyResponse> getGroupAll(
            @RequestHeader final String authorization
    ){
        try{
            GroupAllResponse schoolAllResponse = groupUsecase.execute(authorization);
            return ResponseEntity.ok(schoolAllResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @GetMapping("/")
    public ResponseEntity<BodyResponse> getGroup(
            @RequestHeader final String token,
            @RequestHeader final String authorization
    ){
        try{
            GroupResponse schoolResponse = groupUsecase.execute(token, authorization, Functions.SELECT);
            return ResponseEntity.ok(schoolResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<BodyResponse> getGroupEdit(
            @RequestBody final GroupRequest groupRequest,
            @RequestHeader final String authorization
    ){
        try{
            GroupResponse schoolResponse = groupUsecase.execute(groupRequest, authorization);
            return ResponseEntity.ok(schoolResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @PostMapping("/edit")
    public ResponseEntity<BodyResponse> getGroupEdit(
            @RequestBody final GroupRequest groupRequest,
            @RequestHeader final String authorization,
            @RequestHeader final String token
    ){
        try{
            GroupResponse schoolResponse = groupUsecase.execute(groupRequest, authorization, token);
            return ResponseEntity.ok(schoolResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<BodyResponse> getGroupDel(
            @RequestBody final GroupRequest groupRequest,
            @RequestHeader final String authorization,
            @RequestHeader final String token
    ){
        try{
            GroupResponse schoolResponse = groupUsecase.execute(token, authorization, Functions.DELETE);
            return ResponseEntity.ok(schoolResponse);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(ErrorResponseAdapter.toErrorResponse(e, "400"));
        }
    }
}
