import {
  Box,
  Button,
  Heading,
  HStack,
  Image,
  Modal,
  ModalBody,
  ModalCloseButton,
  ModalContent,
  ModalFooter,
  ModalHeader,
  ModalOverlay,
  Text,
  useDisclosure,
  VStack,
} from "@chakra-ui/react";
import React, { useState } from "react";
import { FaTrash } from "react-icons/fa";
import { getProductContext } from "../context/ContextDemo";

const Cart2: React.FC = () => {
  const { cart, setCart } = getProductContext();
  const [quantities, setQuantities] = useState<number[]>(
    Array(cart.length).fill(1)
  );
  const [currentItemIndex, setCurrentItemIndex] = useState<number | null>(null);
  const { isOpen, onOpen, onClose } = useDisclosure();

  const handleQuantityChange = (index: number, value: number) => {
    if (value === 0) {
      // Open the confirmation modal
      setCurrentItemIndex(index);
      onOpen();
      return;
    }
    const newQuantities = [...quantities];
    newQuantities[index] = Math.max(1, value); // Minimum quantity is 1
    setQuantities(newQuantities);
  };

  const confirmRemoveItem = () => {
    if (currentItemIndex !== null) {
      const updatedCart = cart.filter((_, i) => i !== currentItemIndex);
      setCart(updatedCart);
      setQuantities(quantities.filter((_, i) => i !== currentItemIndex));
      setCurrentItemIndex(null);
    }
    onClose();
  };

  const handleRemoveFromCart = (index: number) => {
    const updatedCart = cart.filter((_, i) => i !== index);
    setCart(updatedCart);
    setQuantities(quantities.filter((_, i) => i !== index));
  };

  const handleClearCart = () => {
    setCart([]);
    setQuantities([]);
  };

  const totalPrice = cart.reduce(
    (sum, product, index) => sum + product.price * quantities[index],
    0
  );

  return (
    <Box px={8} py={6} bg="gray.50" minH="100vh">
      <Heading textAlign="center" mb={6} color="blue.600">
        Shopping Cart
      </Heading>
      {cart.length > 0 ? (
        <VStack spacing={6}>
          {cart.map((product, index) => (
            <Box
              key={product.id}
              border="1px"
              borderColor="gray.200"
              borderRadius="md"
              p={4}
              boxShadow="sm"
              bg="white"
              w="100%"
            >
              <HStack spacing={4} alignItems="flex-start">
                <Image
                  src={product.image}
                  alt={product.title}
                  boxSize="100px"
                  objectFit="contain"
                />
                <VStack alignItems="flex-start" flex="1" spacing={2}>
                  <Heading size="sm" color="blue.600" noOfLines={1}>
                    {product.title}
                  </Heading>
                  <Text fontSize="sm" color="gray.500">
                    {product.description}
                  </Text>
                  <Text fontWeight="bold" color="blue.600">
                    ${product.price.toFixed(2)}
                  </Text>
                </VStack>
                <VStack>
                  {/* Quantity Controls */}
                  <HStack>
                    <Button
                      size="sm"
                      onClick={() =>
                        handleQuantityChange(index, quantities[index] - 1)
                      }
                    >
                      -
                    </Button>
                    <input
                      type="number"
                      value={quantities[index]}
                      onChange={(e) =>
                        handleQuantityChange(
                          index,
                          parseInt(e.target.value) || 1
                        )
                      }
                      min={0}
                      style={{
                        width: "50px",
                        textAlign: "center",
                        border: "1px solid #ddd",
                        borderRadius: "4px",
                      }}
                    />
                    <Button
                      size="sm"
                      onClick={() =>
                        handleQuantityChange(index, quantities[index] + 1)
                      }
                    >
                      +
                    </Button>
                  </HStack>
                  <Button
                    size="sm"
                    colorScheme="red"
                    leftIcon={<FaTrash />}
                    onClick={() => handleRemoveFromCart(index)}
                  >
                    Remove
                  </Button>
                </VStack>
              </HStack>
            </Box>
          ))}
          <Box textAlign="center" mt={4}>
            <Heading size="md" color="blue.600">
              Total: ${totalPrice.toFixed(2)}
            </Heading>
            <Button
              mt={4}
              colorScheme="blue"
              size="sm"
              onClick={handleClearCart}
            >
              Clear Cart
            </Button>
          </Box>
        </VStack>
      ) : (
        <Text textAlign="center" fontSize="lg" color="gray.500">
          Your cart is empty!
        </Text>
      )}

      {/* Confirmation Modal */}
      <Modal isOpen={isOpen} onClose={onClose}>
        <ModalOverlay />
        <ModalContent>
          <ModalHeader>Remove Item</ModalHeader>
          <ModalCloseButton />
          <ModalBody>
            Are you sure you want to remove this item from your cart?
          </ModalBody>
          <ModalFooter>
            <Button colorScheme="blue" mr={3} onClick={onClose}>
              Cancel
            </Button>
            <Button colorScheme="red" onClick={confirmRemoveItem}>
              Remove
            </Button>
          </ModalFooter>
        </ModalContent>
      </Modal>
    </Box>
  );
};

export default Cart2;
