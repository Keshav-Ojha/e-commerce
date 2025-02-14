import { createContext, useContext, useState, useEffect } from "react";
import { Product } from "../types/types";

type Props = {
  children: React.ReactNode;
};

type ProductContextType = {
  products: Product[];
  cart: Product[];
  setCart: React.Dispatch<React.SetStateAction<Product[]>>;
};

type AuthContextType = {
  isAuthenticated: boolean;
  setIsAuthenticated: React.Dispatch<React.SetStateAction<boolean>>;
};

export const ProductContext = createContext<ProductContextType>({
  products: [],
  cart: [],
  setCart: () => {},
});

export const AuthContext = createContext<AuthContextType>({
  isAuthenticated: false,
  setIsAuthenticated: () => false,
});

const ContextDemo = ({ children }: Props) => {
  const [cart, setCart] = useState<Product[]>([]);
  const [products, setProducts] = useState<Product[]>([]);
  const [isAuthenticated, setIsAuthenticated] = useState<boolean>(false);

  // Fetch products from products.json on component mount
  useEffect(() => {
    const fetchProducts = async () => {
      try {
        const response = await fetch("/assets/products.json");
        if (!response.ok) {
          throw new Error("Failed to load products.json");
        }
        const data = await response.json();
        setProducts(data);
      } catch (error) {
        console.error("Error fetching products:", error);
      }
    };

    fetchProducts();
  }, []);

  return (
    <ProductContext.Provider value={{ products, cart, setCart }}>
      <AuthContext.Provider value={{ isAuthenticated, setIsAuthenticated }}>
        {children}
      </AuthContext.Provider>
    </ProductContext.Provider>
  );
};

export const getProductContext = () => {
  return useContext(ProductContext);
};

export const getAuthContext = () => {
  return useContext(AuthContext);
};

export default ContextDemo;
