// src/App.tsx
import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import Layout from "./components/Layout";
import Cart2 from "./pages/Cart2";
import Home from "./pages/Home";
import Products from "./pages/Products";
import Login from "./pages/Login";
import ProtectedRoutes from "./components/ProtectedRoutes";

const App: React.FC = () => {
  return (
    <Router>
      <Routes>
        {/* Layout with Navbar */}
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
          <Route path="products" element={<Products />} />
          <Route path="login" element={<Login />} />
          <Route path="auth" element={<ProtectedRoutes />}>
            <Route path="cart" element={<Cart2 />} />
          </Route>
        </Route>
      </Routes>
    </Router>
  );
};

export default App;
