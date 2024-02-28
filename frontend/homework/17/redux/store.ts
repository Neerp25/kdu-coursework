import { configureStore } from "@reduxjs/toolkit";
import counterReducer  from "./CounterSlice";
import productsReducer from "./ProductSlice";
import snackbarReducer from "./SnackbarSlice";

export const store = configureStore({
    reducer: {
        counter: counterReducer,
        products: productsReducer,
        snackbar: snackbarReducer,
    }
});

export type RootState = ReturnType<typeof store.getState>
export type DispatchType = typeof store.dispatch