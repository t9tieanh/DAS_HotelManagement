import logo from './logo.svg';
import { Outlet } from 'react-router-dom';
import { useEffect } from 'react';


const App = () => {

  return (
    <div className="App">
      <Outlet />
    </div>
  );
}

export default App;
