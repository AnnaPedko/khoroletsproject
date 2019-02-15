package com.khorolets.dao;
public interface OrderDao {
    /**
     * Order product
     *
     * @param clientId
     *        id of client who orders product;
     * @param productId
     *        product id;
     * @return true if order have been done ;
     **/
    boolean orderProduct(long clientId, long productId);

    /**
     * Show list of all orders
     **/
    void showOrders();

    /**
     * Show list of orders for certain client id
     *
     * @param clientId
     *        client id;
     **/
    void showOrdersByClientId(long clientId);

    /**
     * Delete all orders for certain client id
     *
     * @param clientId
     *        client id;
     **/
    void deleteOrdersByClientId(long clientId);
}
