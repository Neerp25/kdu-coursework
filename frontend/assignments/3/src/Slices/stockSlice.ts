import { createSlice, PayloadAction } from '@reduxjs/toolkit';

interface Stock {
  id: string;
  name: string;
  price: number;
}

interface StocksState {
  stocks: Stock[];
  wishlist: Stock[];
}

const initialState: StocksState = {
  stocks: [],
  wishlist: [],
};

const stocksSlice = createSlice({
  name: 'stocks',
  initialState,
  reducers: {
    setStocks: (state, action: PayloadAction<Stock[]>) => {
      state.stocks = action.payload;
    },
    addToWishlist: (state, action: PayloadAction<Stock>) => {
      state.wishlist.push(action.payload);
    },
    removeFromWishlist: (state, action: PayloadAction<string>) => {
      state.wishlist = state.wishlist.filter(stock => stock.id !== action.payload);
    },
  },
});

export const { setStocks, addToWishlist,removeFromWishlist } = stocksSlice.actions;

export default stocksSlice.reducer;