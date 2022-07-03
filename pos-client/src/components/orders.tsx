import { Box, Heading, Spinner, Text } from "@chakra-ui/react";
import { useCallback, useEffect, useState } from "react";
import { http } from "../axios";
import { useAppSelector } from "../store";
import Order from "../types/order";

const Orders = () => {
  const cart = useAppSelector((state) => state.cart.cart);
  const [orders, setOrders] = useState<Order[]>();

  const loadOrders = useCallback(() => {
    http
      .get<Order[]>(`/api/orders`, { params: { userId: "demo" } })
      .then((response) => setOrders(response.data));
  }, []);

  useEffect(() => {
    loadOrders();
  }, [cart]);

  return (
    <>
      <Heading size="sm">订单列表</Heading>
      <Box my={3}>
        {!orders ? (
          <Spinner />
        ) : (
          <>
            {orders.map((order) => (
              <Text>
                {order.id} [${order.total}]
              </Text>
            ))}
          </>
        )}
      </Box>
    </>
  );
};

export default Orders;
