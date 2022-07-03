type Order = {
  id: string;
  userId: string;
  items: { [productId: string]: number };
  total: number;
};

export default Order;
