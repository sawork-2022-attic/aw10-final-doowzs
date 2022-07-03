import { createAsyncThunk, createSlice, PayloadAction } from "@reduxjs/toolkit";
import { http } from "../axios";
import Cart from "../types/cart";
import Order from "../types/order";

type CartState = {
  cart: Cart | undefined;
};

const initialState: CartState = {
  cart: undefined,
};

export const loadCart = createAsyncThunk("cart/loadCart", async () => {
  const response = await http.get<Cart>(`/api/carts/demo`);
  return response.data;
});

export const addItem = createAsyncThunk(
  "cart/addItem",
  async ({ productId, quantity }: { productId: string; quantity: number }) => {
    const response = await http.post<Cart>(
      `/api/carts/demo/items/add`,
      {},
      {
        params: {
          productId: productId,
          quantity: quantity,
        },
      }
    );
    return response.data;
  }
);

export const removeItem = createAsyncThunk(
  "cart/removeItem",
  async ({ productId }: { productId: string }) => {
    const response = await http.post<Cart>(
      `/api/carts/demo/items/remove`,
      {},
      {
        params: {
          productId: productId,
        },
      }
    );
    return response.data;
  }
);

export const checkout = createAsyncThunk("cart/checkout", async () => {
  const response = await http.post<Order>(
    `/api/orders`,
    {},
    {
      params: {
        userId: "demo",
      },
    }
  );
  return response.data;
});

export const cartSlice = createSlice({
  name: "cart",
  initialState: initialState,
  reducers: {},
  extraReducers: (builder) => {
    builder
      .addCase(loadCart.fulfilled, (state, action: PayloadAction<Cart>) => {
        state.cart = action.payload;
      })
      .addCase(addItem.fulfilled, (state, action: PayloadAction<Cart>) => {
        state.cart = action.payload;
      })
      .addCase(removeItem.fulfilled, (state, action: PayloadAction<Cart>) => {
        state.cart = action.payload;
      })
      .addCase(checkout.fulfilled, (state) => {
        state.cart = { userId: "demo", items: {} };
      });
  },
});

export default cartSlice.reducer;
