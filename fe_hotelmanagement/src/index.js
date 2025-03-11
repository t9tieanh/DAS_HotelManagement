import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Provider } from 'react-redux';
import store from './redux/store';
import { RouterProvider, createBrowserRouter } from 'react-router-dom';
import HomeLayout from './layouts/HomeLayout';
import HomePage from './pages/HomePage/index.jsx';
import RoomsPage from './pages/RoomList/index.jsx';
import RoomDetail from './pages/RoomDetail/index.jsx';
import NotFoundPage from './pages/NotFoundPage/index.jsx';
import LoginPage from './pages/LoginPage/index.jsx';

const root = ReactDOM.createRoot(document.getElementById('root'));

const router = createBrowserRouter([
  {
    path: '',
    element: <App />,
    children: [
      { path: '/', element: <HomeLayout />, children: [
        { index: true, element: <HomePage /> },
        { path: 'rooms', element: <RoomsPage /> },
        { path: 'login', element: <LoginPage /> },
        { path: 'room/:id', element: <RoomDetail /> }
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
    <React.StrictMode>
    <RouterProvider router={router} />
    </React.StrictMode>
  </Provider>
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
