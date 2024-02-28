import { createSlice } from "@reduxjs/toolkit";
import { getProducts } from "./thunks/getProducts";
import { ProductInfo } from "./ProductInfo";

interface ProductState {
  products: ProductInfo[];
  state: "pending" | "fulfilled" | "error";
  error?: string;
}

const initialState: ProductState = {
  products: [],
  state: "pending",
};

export const productSlice = createSlice({
  name: "products",
  initialState,  
  reducers: {

  },
  extraReducers(builder) {
      builder.addCase(getProducts.pending,(state)=>{
        state.state="pending"
      }).addCase(getProducts.fulfilled,(state,action)=>{
        state.products=action.payload;
        state.state="fulfilled";
      }).addCase(getProducts.rejected,(state,action)=>{
        state.error = action.payload as string
        state.state ="error"
      })
  },
});

export default productSlice.reducer;