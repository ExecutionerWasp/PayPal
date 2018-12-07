package org.strategies;

import lombok.NonNull;
import org.domain.Order;

import java.util.Objects;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 * @autor Alvin
 **/
public class StrategyController {
    @Deprecated
    private final int CUSTOM_TOTAL_COST = 10_000;

    private final Logger log;
    private Order order;
    private PayStrategy strategy;

    public StrategyController (@NonNull Order order) {
        this.order = order;
        log = Logger.getLogger(StrategyController.class.getName());
    }

    public Order transacting () {
        this.order = Stream.of(this.order)
                .filter(order -> ! order.isClosed())
                .peek(order -> order.setTotalCost(CUSTOM_TOTAL_COST))
                .findFirst().get();

        this.strategy = Stream.of(this.strategy)
                .filter(Objects::isNull)
                .peek(strategy -> log.info("Strategy nut null"))
                .map(strategy -> strategy = new PayPalStrategy())
                .peek(strategy -> log.info("Strategy:" + PayPalStrategy.class.getName()))
                .findFirst().get();

        this.order.processOrder(this.strategy);
        log.info("Pay " + order.getTotalCost());

        if (strategy.pay(order.getTotalCost())) {
            log.info("Payment has been successful.");
        } else {
            log.warning("FAIL! Please, check your data.");
            //TODO: create exception
            return null;
        }

        order.setClosed();

        log.info("Order has been closed.");

        return this.order;
    }

    public PayStrategy getStrategy(){
        return this.strategy;
    }
}
