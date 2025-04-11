import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import HomeLayout from './layouts/HomeLayout';
import HomePage from './pages/HomePage/index.jsx';
import NotFoundPage from './pages/NotFoundPage/index.jsx';
import LoginPage from './pages/LoginPage/index.jsx';
import RegisterPage from './pages/RegisterPage/index.jsx';
import HotelSearchPage from './pages/HotelSearchPage/index.jsx';

//redux persit 
import { PersistGate } from 'redux-persist/integration/react';
import { Provider } from 'react-redux';
import { store, persistor } from './redux/store';
import AuthenticatePage from './pages/GGAuth/Authenticate/index.jsx';
import HotelDetail from './pages/HotelDetail/index.jsx';
import CustomerProfile from './pages/CustomerProfile/index.jsx';
import VerifyOTP from './pages/VerifyOTP/index.jsx';
import ReservationCheckOut from './pages/CheckOut/ConfirmInfomationPage/index.jsx';
import PaymentLayout from './layouts/PaymentLayout/index.jsx';
import CheckOutPage from './pages/CheckOut/CheckoutPage/index.jsx';
import PrivateBookingRoute from './routes/PrivateBookingRoute.jsx';
import VNPayCallback from './services/PaymentService/VNPayCallback.js';
import ReservationSuccess from './pages/CheckOut/ReservationSuccess/index.jsx';
import AboutUsPage from './pages/AboutUs/index.jsx';


const root = ReactDOM.createRoot(document.getElementById('root'));

const router = createBrowserRouter([
  {
    path: '',
    element: <App />,
    children: [
      {
        path: 'reservation', element: <PaymentLayout />, children: [
          { index: true, element: <PrivateBookingRoute children={<CheckOutPage />} /> },
        ]
      },

      {
        path: '/test', element: <ReservationSuccess />
      },
      {
        path: '/', element: <HomeLayout />, children: [
          { index: true, element: <HomePage /> },
          { path: 'about-us', element: <AboutUsPage /> },
          { path: 'hotel-detail/:id', element: <HotelDetail /> },
          { path: 'login', element: <LoginPage /> },
          { path: 'authentication', element: <AuthenticatePage /> },
          { path: 'register', element: <RegisterPage /> },
          { path: 'hotel-result', element: <HotelSearchPage /> },
          { path: 'profile', element: <CustomerProfile /> },
          { path: 'verify-otp', element: <VerifyOTP /> },
        ]
      },
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
