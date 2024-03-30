package pizzashop.blackbox;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.service.PizzaService;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PizzaServiceTest {

    private PizzaService service;

    private PaymentRepository payRepo;

    @BeforeEach
    void setUp() {
        MenuRepository repoMenu = new MenuRepository();
        this.payRepo = new PaymentRepository();
        this.service = new PizzaService(repoMenu, payRepo);

        payRepo.getAll().clear();
        payRepo.writeAll();
    }

    @AfterEach
    void tearDown() {
        payRepo.getAll().clear();
        payRepo.writeAll();
    }

    @Test
    @Disabled
    void disabledTest() {
        Payment payment = new Payment(1, PaymentType.CASH, 10);

        assertEquals(10, payment.getAmount());
    }

    @Test
    @Order(1)
    @DisplayName("Test for adding a valid payment (EC)")
    void addValidPaymentTest() {
        service.addPayment(2, PaymentType.CASH, 20.99);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(2)
    void addInvalidPaymentNegativeAmountTest() {
        service.addPayment(3, PaymentType.CASH, -20.99);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(3)
    void addInvalidPaymentNullTypeTest() {
        service.addPayment(4, null, 23.99);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(4)
    void addInvalidPaymentNullTypeInvalidAmountTest() {
        service.addPayment(5, null, 0);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(5)
    void addInvalidPaymentInvalidTableTest() {
        service.addPayment(0, PaymentType.CARD, 20.99);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(6)
    void addInvalidPaymentInvalidTableInvalidAmountTest() {
        service.addPayment(10, PaymentType.CASH, 2000);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(7)
    void addInvalidPaymentInvalidTableInvalidTypeTest() {
        service.addPayment(-1, null, 99.99);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(8)
    void addInvalidPaymentInvalidTypeInvalidAmountTest() {
        service.addPayment(20, null, 2001);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(9)
    @Tag("BVA")
    @DisplayName("Test for adding a valid payment (BVA)")
    void addValidPaymentBVA1Test() {
        service.addPayment(5, PaymentType.CARD, 29.99);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(10)
    @EnabledOnOs(OS.WINDOWS)
    void addValidPaymentBVA2Test() {
        service.addPayment(1, PaymentType.CARD, 29.99);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(11)
    void addValidPaymentBVA3Test() {
        service.addPayment(9, PaymentType.CARD, 29.99);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(12)
    void addInvalidPaymentBVA4Test() {
        service.addPayment(0, PaymentType.CARD, 29.99);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(13)
    void addInvalidPaymentBVA5Test() {
        service.addPayment(-5, PaymentType.CARD, 29.99);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(14)
    void addInvalidPaymentBVA6Test() {
        service.addPayment(10, PaymentType.CARD, 29.99);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(15)
    void addInvalidPaymentBVA7Test() {
        service.addPayment(15, PaymentType.CARD, 29.99);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(16)
    void addInvalidPaymentBVA8Test() {
        service.addPayment(5, PaymentType.CASH, 29.99);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(17)
    void addInvalidPaymentBVA9Test() {
        service.addPayment(5, null, 29.99);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(18)
    void addInvalidPaymentBVA10Test() {
        service.addPayment(5, PaymentType.CARD, 1000);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    @Order(19)
    void addInvalidPaymentBVA11Test() {
        service.addPayment(5, PaymentType.CARD, 0);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(20)
    void addInvalidPaymentBVA12Test() {
        service.addPayment(5, PaymentType.CARD, 2000);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(21)
    void addInvalidPaymentBVA13Test() {
        service.addPayment(5, PaymentType.CARD, -1);

        assertEquals(0, service.getPayments().size());
    }

    @Test
    @Order(22)
    void addInvalidPaymentBVA14Test() {
        service.addPayment(5, PaymentType.CARD, 2050);

        assertEquals(0, service.getPayments().size());
    }
}
