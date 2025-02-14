// src/components/ProductCard.tsx
import React from "react";
import {
  Box,
  Image,
  Text,
  Heading,
  HStack,
  Button,
  Badge,
} from "@chakra-ui/react";
import { Product } from "../types/types";
import { getProductContext } from "../context/ContextDemo";

const ProductCard: React.FC<{ product: Product }> = ({ product }) => {
  const { cart, setCart } = getProductContext();

  // Function to handle adding product to cart
  const handleAddToCart = () => {
    // Check if product is already in cart
    const isAlreadyInCart = cart.some((item) => item.id === product.id);
    if (!isAlreadyInCart) {
      setCart([...cart, product]); // Add product to cart
    } else {
      alert("Product is already in the cart!");
    }
  };

  return (
    <Box
      border="1px"
      borderColor="gray.200"
      borderRadius="md"
      overflow="hidden"
      boxShadow="md"
      p={4}
      bg="white"
      className="hover:shadow-lg transition-shadow"
    >
      <Image src={product.image} alt={product.title} height="200px" mx="auto" />
      <Box mt={4}>
        <Heading size="md" color="blue.600" className="truncate">
          {product.title}
        </Heading>
        <Text color="gray.500" fontSize="sm" mt={2}>
          {product.category}
        </Text>
        <Text fontSize="sm" mt={2} noOfLines={2}>
          {product.description}
        </Text>
        <HStack justifyContent="space-between" mt={4}>
          <Text fontWeight="bold" fontSize="lg" color="blue.500">
            ${product.price}
          </Text>
          <Badge colorScheme="green">{product.rating.rate} ★</Badge>
        </HStack>
        <Button colorScheme="blue" mt={4} w="full" onClick={handleAddToCart}>
          Add to Cart
        </Button>
      </Box>
    </Box>
  );
};

export default ProductCard;
