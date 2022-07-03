type Cart = {
  userId: string;
  items: { [productId: string]: number };
};

export default Cart;
