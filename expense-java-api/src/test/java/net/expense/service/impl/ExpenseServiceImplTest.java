package net.expense.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@SpringBootTest
@TestExecutionListeners({
  DependencyInjectionTestExecutionListener.class,
  MockitoTestExecutionListener.class
})
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
class ExpenseServiceImplTest {

  @Test
  final void testExpenseServiceImpl() {
    fail("Not yet implemented"); // TODO
  }

  @Test
  final void testSave() {
  }

  @Test
  final void testFindAll() {
    fail("Not yet implemented"); // TODO
  }

  @Test
  final void testExistsById() {
    fail("Not yet implemented"); // TODO
  }

  @Test
  final void testFindById() {
    fail("Not yet implemented"); // TODO
  }
}
