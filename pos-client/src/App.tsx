import { Divider, Grid, GridItem } from "@chakra-ui/react";
import MyCart from "./components/cart";
import Layout from "./components/layout";
import Orders from "./components/orders";
import Products from "./components/products";

const App = () => {
  return (
    <>
      <Layout title="狗东购物商城">
        <Grid templateColumns="repeat(4, 1fr)" gap={5}>
          <GridItem colSpan={3}>
            <Products />
          </GridItem>
          <GridItem colSpan={1}>
            <MyCart />
            <Divider my={5} />
            <Orders />
          </GridItem>
        </Grid>
      </Layout>
    </>
  );
};

export default App;
