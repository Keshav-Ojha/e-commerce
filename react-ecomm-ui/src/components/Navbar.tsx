import React from "react";
import {
  Box,
  HStack,
  Heading,
  Button,
  Menu,
  MenuButton,
  MenuList,
  MenuItem,
  IconButton,
  Badge,
} from "@chakra-ui/react";
import { FaShoppingCart, FaUserCircle } from "react-icons/fa";
import { Link } from "react-router-dom";
import { getProductContext } from "../context/ContextDemo";

const Navbar: React.FC = () => {
  const { cart } = getProductContext();

  return (
    <Box className="bg-white shadow-md px-6 py-4">
      <HStack justifyContent="space-between" alignItems="center">
        {/* Logo + Navigation */}
        <HStack spacing={8}>
          <Heading
            size="lg"
            color="blue.600"
            className="font-bold cursor-pointer"
          >
            <Link to="/">LuxeApparel</Link>
          </Heading>
          <HStack spacing={6}>
            <Button as={Link} to="/" variant="link" colorScheme="blue">
              Home
            </Button>
            <Button as={Link} to="/products" variant="link" colorScheme="blue">
              Products
            </Button>
          </HStack>
        </HStack>

        {/* User and Cart Icons */}
        <HStack spacing={6}>
          {/* User Dropdown */}
          <Menu>
            <MenuButton
              as={IconButton}
              icon={<FaUserCircle />}
              variant="ghost"
              aria-label="User Options"
              size="lg"
            />
            <MenuList>
              <MenuItem>Profile</MenuItem>
              <MenuItem>Orders</MenuItem>
              <MenuItem>Logout</MenuItem>
            </MenuList>
          </Menu>

          {/* Cart Icon */}
          <Box position="relative">
            <IconButton
              as={Link}
              to="/auth/cart"
              icon={<FaShoppingCart />}
              aria-label="Cart"
              size="lg"
              variant="ghost"
            />
            <Badge
              colorScheme="red"
              position="absolute"
              top="-2px"
              right="-2px"
              rounded="full"
              fontSize="0.8em"
              px={2}
            >
              {cart.length}
            </Badge>
          </Box>
        </HStack>
      </HStack>
    </Box>
  );
};

export default Navbar;
