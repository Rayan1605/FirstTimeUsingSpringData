package guru.springframework.spring6restmvc.Controller;

import guru.springframework.spring6restmvc.Customer.CustomerDto;
import guru.springframework.spring6restmvc.Repositories.CustomerRepostiory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
//Integration test
@SpringBootTest // Now we are brining the full spring context not the bare minimum

class ControllerIntegration {
    @Autowired //so we can get the fully complete controller
Controller controller;

    @Autowired // so we can get the fully complete repository
    CustomerRepostiory customerRepostiory;
@Test
    void testListCustomers(){
        List<CustomerDto> dto = controller.returnCustomer();
       assertThat(dto.size() == 3);
    }
    @Test
    @Transactional
    @Rollback

    // the @Transactional annotation is used to mark a method or a class as being part of a
        // transaction. Transactions ensure that a group of related database operations
        // either succeed or fail together. When a method or class is marked with @Transactional:
        //
        //It tells the system to handle the transaction automatically.
        //A transaction is started when the annotated method is called.
        //If the method executes successfully, the changes made within the
        // transaction are saved permanently when the method finishes.
        //If an error occurs or an exception is thrown, the changes made within
        // the transaction are rolled back, so it's as if the changes never happened.

    //The rollback operation is an important concept in transactional systems.
        // When a transaction is rolled back, it means that any changes made within
        // the transaction are undone or reverted, effectively canceling those changes.
        // The main purpose of a rollback is to restore the data to its previous consistent state.
    void TestEmptyList(){
        customerRepostiory.deleteAll();
        List<CustomerDto> dto = controller.returnCustomer();
        assertThat(dto.size() == 0);
    }


}