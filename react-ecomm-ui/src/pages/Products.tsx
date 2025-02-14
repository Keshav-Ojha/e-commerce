// src/pages/Products.tsx
import React from "react";
import { Box, Grid, Heading } from "@chakra-ui/react";
import ProductCard from "../components/ProductCard";
import { getProductContext } from "../context/ContextDemo";

const Products: React.FC = () => {
  // Get products from the Context API
  const { products } = getProductContext();

  return (
    <Box px={8} py={6} bg="gray.50" minH="100vh">
      <Heading textAlign="center" mb={6} color="blue.600">
        Our Products
      </Heading>
      <Grid
        templateColumns="repeat(auto-fill, minmax(250px, 1fr))"
        gap={6}
        className="w-full"
      >
        {products.map((product) => (
          <ProductCard key={product.id} product={product} />
        ))}
      </Grid>
    </Box>
  );
};

export default Products;
