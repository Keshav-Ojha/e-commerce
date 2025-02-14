// src/components/Layout.tsx
import React from "react";
import { Box } from "@chakra-ui/react";
import Navbar from "./Navbar";
import { Outlet } from "react-router-dom";

const Layout: React.FC = () => {
  return (
    <Box>
      {/* Navbar at the top */}
      <Navbar />

      {/* Page content will be rendered here */}
      <Box mt={4}>
        <Outlet />
      </Box>
    </Box>
  );
};

export default Layout;
