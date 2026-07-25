package jasmineorder.Order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jasmineorder.Order.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}