package net.expense.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.ValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import net.expense.model.Expense;
import net.expense.model.Tag;
import net.expense.service.ExpenseService;
import net.expense.service.TagService;
import net.expenseclient.model.dto.ExpenseDTO;

@RestController
@RequestMapping("/v1/expenses")
public class ExpenseController {

    private ExpenseService expenseService;
    private TagService tagService;
    private ModelMapper modelMapper;

    @Autowired
    private ExpenseController(ExpenseService expenseService, TagService tagService, ModelMapper modelMapper) {
        this.expenseService = expenseService;
        this.tagService = tagService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ApiOperation(value = "Route to create an expense")
    public ResponseEntity<ExpenseDTO> create(@Valid @RequestBody ExpenseDTO dto, BindingResult result)
            throws ValidationException {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }
        List<Tag> savedTags = saveTags(dto);
        Expense entity = convert(dto);
        entity.setTags(savedTags);
        ExpenseDTO saved = convert(expenseService.save(entity));
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    private List<Tag> saveTags(ExpenseDTO dto) {
        List<Tag> savedTags = Arrays.stream(dto.getTags().split(",")).map(tagName -> {
            String tagStrip = tagName.strip().toLowerCase();
            return this.tagService.findByName(tagStrip).map(found -> {
                return found;
            }).orElseGet(() -> {
                Tag tag = new Tag();
                tag.setName(tagStrip);
                return this.tagService.save(tag);
            });
        }).collect(Collectors.toList());
        return savedTags;
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Route to update an expense")
    public ResponseEntity<ExpenseDTO> update(@PathVariable final Long id, @Valid @RequestBody ExpenseDTO dto,
            BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            throw new ValidationException(result.getAllErrors().stream().map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(",")));
        }

        return expenseService.findById(id).map(expense -> {

            // Arrays.stream(dto.getTags().split(",")).map(tagName -> {
            // return null;
            // });

            // List<Tag> tags = this.tagService.findByExpense(id);

            expenseService.save(convert(dto));
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }).orElseGet(() -> {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        });
    }

    @GetMapping
    @ApiOperation(value = "Route to find all expenses")
    public ResponseEntity<List<ExpenseDTO>> findAll() {
        List<Expense> expenses = expenseService.findAll();
        List<ExpenseDTO> dtos = expenses.stream().map(this::convert).collect(Collectors.toList());
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Route to find expense by id")
    public ResponseEntity<ExpenseDTO> findById(@PathVariable final Long id) {
        return expenseService.findById(id).map(expense -> {
            return new ResponseEntity<>(convert(expense), HttpStatus.OK);
        }).orElseGet(() -> {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        });
    }

    private ExpenseDTO convert(Expense expense) {
        ExpenseDTO dto = this.modelMapper.map(expense, ExpenseDTO.class);
        dto.setTags(expense.getTags().stream().map(Tag::getName).collect(Collectors.joining(",")));
        return dto;
    }

    private Expense convert(ExpenseDTO dto) {
        return this.modelMapper.map(dto, Expense.class);
    }

}
