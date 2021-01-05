package net.expenseclient.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiOperation;
import net.expenseclient.model.dto.ExpenseDTO;

@FeignClient(
    name = "${expense.service.name}",
    path = "${expense.service.path}",
    url = "${expense.service.url}")
public interface ExpenseClientApi {

  @PostMapping
  @ApiOperation(value = "Route to create an expense")
  public ResponseEntity<ExpenseDTO> create(@RequestBody ExpenseDTO dto);

  @GetMapping
  @ApiOperation(value = "Route to find all expenses")
  public ResponseEntity<List<ExpenseDTO>> findAll();

  @PutMapping("/{id}")
  @ApiOperation(value = "Route to update an expense")
  public ResponseEntity<ExpenseDTO> update(
      @PathVariable final Long id, @Valid @RequestBody ExpenseDTO dto);

  @GetMapping("/{id}")
  @ApiOperation(value = "Route to find expense by id")
  public ResponseEntity<ExpenseDTO> findById(@PathVariable final Long id);
}
