import { createStore, applyMiddleware, compose } from 'redux';
import thunk from 'redux-thunk';
import rootReducer from './reducer/rootReducer';
import { composeWithDevTools } from 'redux-devtools-extension';
import { persistStore, persistReducer } from 'redux-persist';
import storage from 'redux-persist/lib/storage'; // Mặc định là localStorage trên web

// Cấu hình redux-persist
const persistConfig = {
  key: 'root',
  storage,
};

const persistedReducer = persistReducer(persistConfig, rootReducer);

// Áp dụng middleware + Redux DevTools
const middleware = [thunk];
const composedEnhancers = composeWithDevTools(applyMiddleware(...middleware));

const store = createStore(persistedReducer, composedEnhancers);
const persistor = persistStore(store);

export { store, persistor };
