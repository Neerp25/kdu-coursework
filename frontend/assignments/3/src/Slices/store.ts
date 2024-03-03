import { configureStore } from '@reduxjs/toolkit';
import stocksReducer from './stockSlice';
import storage from 'redux-persist/lib/storage';
import { persistReducer } from 'redux-persist';
import { combineReducers } from "@reduxjs/toolkit";
import transactionsReducer from './transactionsSlice';

const persistConfig = {
  key: 'root',
  // version:1,
  storage,
}


const reducer = combineReducers({
  stocks: stocksReducer,
  transactions: transactionsReducer,
})
const persistedReducer = persistReducer(persistConfig,reducer)

export const store = configureStore({
  reducer: persistedReducer,
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;