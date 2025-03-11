import logo from './logo.svg';
import { Outlet } from 'react-router-dom';


const App = () => {

  return (
    <div className="App">
      <Outlet />
    </div>
  );
}

export default App;
