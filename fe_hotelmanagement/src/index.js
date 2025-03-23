import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import HomeLayout from './layouts/HomeLayout';
import HomePage from './pages/HomePage/index.jsx';
import RoomsPage from './pages/RoomList/index.jsx';
import RoomDetail from './pages/RoomDetail/index.jsx';
import NotFoundPage from './pages/NotFoundPage/index.jsx';
import LoginPage from './pages/LoginPage/index.jsx';
import RegisterPage from './pages/RegisterPage/index.jsx';
import HotelSearchPage from './pages/HotelSearchPage/index.jsx';

//redux persit 
import { PersistGate } from 'redux-persist/integration/react';
import { Provider } from 'react-redux';
import {store, persistor} from './redux/store';
import AuthenticatePage from './pages/GGAuth/Authenticate/index.jsx';
import HotelDetail from './pages/HotelDetail/index.jsx';
import CustomerProfile from './pages/CustomerProfile/index.jsx';


const root = ReactDOM.createRoot(document.getElementById('root'));

const router = createBrowserRouter([
  {
    path: '',
    element: <App />,
    children: [
      { path: '/', element: <HomeLayout />, children: [
        { index: true, element: <HomePage /> },
        { path: 'rooms', element: <RoomsPage /> },
        { path: 'hotel-detail', element: <HotelDetail /> },
        { path: 'login', element: <LoginPage /> },
        { path: 'authentication', element: <AuthenticatePage /> },
        { path: 'register', element: <RegisterPage /> },
        { path: 'room/:id', element: <RoomDetail /> },
        { path: 'hotel-result', element: <HotelSearchPage /> },
        { path: 'profile', element: <CustomerProfile /> }
      ]},
    ],
  },
  {
    path: "*",  
    element: <NotFoundPage />
  }
]);



root.render(
  <Provider store={store}>
    <PersistGate loading={null} persistor={persistor}>
      <RouterProvider router={router} />
    </PersistGate>
  </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
