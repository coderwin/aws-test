import MainPage from './page/MainPage.js'
import LoginPage from './page/LoginPage.js'
import SingupPage from './page/SignupPage.js'
import Header from './component/Header.js'
import { Route, Routes, useNavigate } from 'react-router-dom';
import './common/app.css'

function App() {

  // 변수
  const navigation = useNavigate();

  // 상태

  // 메서드

  // view


  return (
    <div className='container'>
      <Header />
      
      <Routes>
        <Route path='/' element={<MainPage />} />

        <Route path='/login' element={<LoginPage />} />
        <Route path='/signup' element={<SingupPage />} />

      </Routes>
    </div>
  );
}

export default App;
