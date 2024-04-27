package pizzashop.blackbox;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
    void addValidPaymentTest() {
        assertEquals(0, service.getPayments().size());

        service.addPayment(2, PaymentType.CASH, 20.99);

        assertEquals(1, service.getPayments().size());
    }

    @Test
    void addInvalidPaymentNullTypeTest() {
        assertEquals(0, service.getPayments().size());
        service.addPayment(4, null, 20.99);
        assertEquals(0, service.getPayments().size());
    }

    @Test
    void addValidPaymentBVA1Test() {
        assertEquals(0, service.getPayments().size());
        service.addPayment(1, PaymentType.CARD, 23.99);
        assertEquals(1, service.getPayments().size());
    }

    @Test
    void addInvalidPaymentBVA9Test() {
        assertEquals(0, service.getPayments().size());
        service.addPayment(5, PaymentType.CASH, -1);
        assertEquals(0, service.getPayments().size());
    }


}
