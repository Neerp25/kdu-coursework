// transactionsSlice.js
import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export interface Transaction {
    stock_symbol: string;
    stock_name: string;
    status: 'Failed' | 'Passed';
    transaction_price: number;
    timestamp: string;
}

interface TransactionsState {
    transactions: Transaction[];
    filteredTransactions: Transaction[];
    filter: string;
    failedFilter: boolean;
    stockFilter: Record<string, boolean>;
    startDate: string | null;
    endDate: string | null;
    loading: boolean;
    showPassedTransactions: boolean;
}

const initialState: TransactionsState = {
    transactions: [],
    filteredTransactions: [],
    filter: '',
    failedFilter: false,
    stockFilter: {},
    startDate: null,
    endDate: null,
    loading: false,
    showPassedTransactions: true,
};

export const transactionsSlice = createSlice({
    name: 'transactions',
    initialState,
    reducers: {
        setTransactions: (state, action: PayloadAction<Transaction[]>) => {
            state.transactions = action.payload;
        },
        setFilter: (state, action: PayloadAction<string>) => {
            state.filter = action.payload;
        },
        setFailedFilter: (state, action: PayloadAction<boolean>) => {
            state.failedFilter = action.payload;
        },
        setStockFilter: (state, action: PayloadAction<Record<string, boolean>>) => {
            state.stockFilter = action.payload;
        },
        setStartDate: (state, action: PayloadAction<string | null>) => {
            state.startDate = action.payload;
        },
        setEndDate: (state, action: PayloadAction<string | null>) => {
            state.endDate = action.payload;
        },
        setLoading: (state, action: PayloadAction<boolean>) => {
            state.loading = action.payload;
        },
        toggleShowPassedTransactions: (state) => {
          state.showPassedTransactions = !state.showPassedTransactions;
        },
        applyFilters: (state) => {
            // Apply filters to transactions and update filteredTransactions
            state.filteredTransactions = state.transactions.filter(transaction => {
                const stockMatches = state.filter ? transaction.stock_symbol.includes(state.filter) || transaction.stock_name.includes(state.filter) : true;
                const statusMatches = state.failedFilter ? transaction.status === 'Failed' : true;
                const dateMatches = (state.startDate || state.endDate) ? (
                    (!state.startDate || new Date(transaction.timestamp) >= new Date(state.startDate)) &&
                    (!state.endDate || new Date(transaction.timestamp) <= new Date(state.endDate))
                ) : true;
                 const stockFilterMatches = Object.keys(state.stockFilter).every(stockSymbol => state.stockFilter[stockSymbol] ? transaction.stock_symbol === stockSymbol : true);
                // return stockMatches && statusMatches && dateMatches && stockFilterMatches;
                const passedTransactionMatches = state.showPassedTransactions ? transaction.status !== 'Failed' : true;
                return stockMatches && statusMatches && dateMatches && stockFilterMatches && passedTransactionMatches;
            });
        },
    },
});

export const {
    setTransactions,
    setFilter,
    setFailedFilter,
    setStockFilter,
    setStartDate,
    setEndDate,
    setLoading,
    applyFilters,
    toggleShowPassedTransactions,
} = transactionsSlice.actions;

export default transactionsSlice.reducer;
