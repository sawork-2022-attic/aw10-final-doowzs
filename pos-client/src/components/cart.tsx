import {
  Box,
  Button,
  Divider,
  Flex,
  Heading,
  HStack,
  Image,
  Spinner,
  Text,
} from "@chakra-ui/react";
import { useCallback, useEffect, useState } from "react";
import { http } from "../axios";
import { addItem, checkout, loadCart, removeItem } from "../slices/cart";
import { useAppDispatch, useAppSelector } from "../store";
import Product from "../types/product";

const CartItem = ({
  productId,
  quantity,
}: {
  productId: string;
  quantity: number;
}) => {
  const [product, setProduct] = useState<Product>();
  const dispatch = useAppDispatch();

  useEffect(() => {
    http.get<Product>(`/api/products/${productId}`).then((response) => {
      setProduct(response.data);
    });
  }, [productId]);

  return (
    <>
      <HStack my={2} gap={2}>
        {product && (
          <>
            <Box minWidth={35} minHeight={35}>
              <Image
                src={product.image}
                ml="auto"
                mr="auto"
                maxWidth={35}
                maxHeight={35}
              />
            </Box>
            <Box w="full">
              <Box>
                <Text>{product.name}</Text>
              </Box>
              <HStack justifyContent="space-between">
                <Text>${product.price}</Text>
                <Flex>
                  <Button
                    size="xs"
                    variant="ghost"
                    onClick={() =>
                      dispatch(
                        addItem({
                          productId: productId,
                          quantity: quantity - 1,
                        })
                      )
                    }
                    disabled={quantity === 1}
                  >
                    -
                  </Button>
                  <Text ml="auto">x{quantity}</Text>
                  <Button
                    size="xs"
                    variant="ghost"
                    onClick={() =>
                      dispatch(
                        addItem({
                          productId: productId,
                          quantity: quantity + 1,
                        })
                      )
                    }
                  >
                    +
                  </Button>
                  <Button
                    ml={3}
                    size="xs"
                    variant="ghost"
                    onClick={() =>
                      dispatch(removeItem({ productId: productId }))
                    }
                  >
                    移除
                  </Button>
                </Flex>
              </HStack>
            </Box>
          </>
        )}
      </HStack>
    </>
  );
};

const MyCart = () => {
  const cart = useAppSelector((state) => state.cart.cart);
  const dispatch = useAppDispatch();

  const [total, setTotal] = useState<number>();

  const loadTotal = useCallback(() => {
    http.get<number>(`/api/carts/demo/total`).then((response) => {
      setTotal(response.data);
    });
  }, []);

  useEffect(() => {
    dispatch(loadCart());
  }, [dispatch]);

  useEffect(() => {
    if (cart) {
      loadTotal();
    }
  }, [cart, loadTotal]);

  return (
    <>
      <Heading size="sm">购物车</Heading>
      <Box my={3}>
        {!cart ? (
          <Spinner />
        ) : (
          <>
            {!Object.keys(cart.items).length ? (
              <Text>购物车内还没有商品</Text>
            ) : (
              <>
                {Object.keys(cart.items).map((productId) => (
                  <CartItem
                    key={productId}
                    productId={productId}
                    quantity={cart.items[productId]}
                  />
                ))}
                <Divider my={3} />
                <Text>
                  总计金额：
                  {!total ? <Spinner size="sm" /> : <>${total}</>}
                </Text>
                <Button w="full" my={2} onClick={() => dispatch(checkout())}>结账</Button>
              </>
            )}
          </>
        )}
      </Box>
    </>
  );
};

export default MyCart;
