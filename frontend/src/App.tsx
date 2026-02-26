import { BrowserRouter, Routes, Route, NavLink } from 'react-router-dom';
import UsersPage from './pages/UsersPage';
import CustomersPage from './pages/CustomersPage';
import ProductsPage from './pages/ProductsPage';
import CasesPage from './pages/CasesPage';

export default function App() {
  return (
    <BrowserRouter>
      <nav>
        <NavLink to="/users">Users</NavLink>
        <NavLink to="/customers">Customers</NavLink>
        <NavLink to="/products">Products</NavLink>
        <NavLink to="/cases">Cases</NavLink>
      </nav>
      <main>
        <Routes>
          <Route path="/users" element={<UsersPage />} />
          <Route path="/customers" element={<CustomersPage />} />
          <Route path="/products" element={<ProductsPage />} />
          <Route path="/cases" element={<CasesPage />} />
          <Route path="/" element={<UsersPage />} />
        </Routes>
      </main>
    </BrowserRouter>
  );
}
