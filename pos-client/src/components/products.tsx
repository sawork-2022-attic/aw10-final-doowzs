import {
  Box,
  Button,
  Flex,
  Grid,
  Heading,
  HStack,
  Image,
  Spinner,
  Text,
} from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import { http } from "../axios";
import { addItem } from "../slices/cart";
import { useAppDispatch } from "../store";
import Product from "../types/product";

const ProductInfo = ({ product }: { product: Product }) => {
  const dispatch = useAppDispatch();

  return (
    <>
      <Box>
        <Box width="5rem" height="5rem">
          <Image src={product.image} maxWidth="5rem" maxHeight="5rem" />
        </Box>
        <HStack w="full" justifyContent="space-between">
          <Text>${product.price}</Text>
          <Flex ml="auto">
            <Button
              onClick={() =>
                dispatch(addItem({ productId: product.id, quantity: 1 }))
              }
            >
              加入购物车
            </Button>
          </Flex>
        </HStack>
        <Box>
          <Text>{product.name}</Text>
        </Box>
      </Box>
    </>
  );
};

const ProductList = ({ products }: { products: Product[] }) => {
  return (
    <>
      <Grid templateColumns="repeat(4, 1fr)" gap={10}>
        {products.map((product) => (
          <React.Fragment key={product.id}>
            <ProductInfo product={product} />
          </React.Fragment>
        ))}
      </Grid>
    </>
  );
};

const Products = () => {
  const [products, setProducts] = useState<Product[]>();

  useEffect(() => {
    http.get<Product[]>(`/api/products`).then((response) => {
      setProducts(response.data.filter((p) => !!p.image));
    });
  }, []);

  return (
    <>
      <Box>
        <Heading size="sm">商品列表</Heading>
        {!products ? (
          <>
            <Spinner />
          </>
        ) : (
          <>
            <ProductList products={products} />
          </>
        )}
      </Box>
    </>
  );
};

export default Products;
