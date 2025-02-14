import { Box, Button, Input, Text, VStack } from "@chakra-ui/react";
import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { getAuthContext } from "../context/ContextDemo";
import { useKeycloak } from "@react-keycloak/web";

function Login() {
  const [isLogin, setIsLogin] = useState(true);
  const { setIsAuthenticated } = getAuthContext();
  const {keycloak} = useKeycloak();

  const navigate = useNavigate();
  const location = useLocation();

  const handleLogin = () => {
    setIsAuthenticated(true);
    navigate(location.state?.from?.pathname || "/");
  };
  return (
    <Box
      display="flex"
      justifyContent="center"
      alignItems="top"
      minHeight="100vh"
      bgGradient="linear(to-r, teal.500, blue.500"
    >
      <Box w="400px" p="6" bg="white" borderRadius="lg" boxShadow="xl">
        <Text
          fontSize="2xl"
          fontWeight="bold"
          textAlign="center"
          mb="4"
          color="blue.500"
        >
          {isLogin ? "Login" : "Sign Up"}
        </Text>

        <VStack spacing="4">
          {!isLogin && (
            <Input
              placeholder="Username"
              size="lg"
              focusBorderColor="teal.400"
            />
          )}
          <Input placeholder="Email" size="lg" focusBorderColor="teal.400" />
          <Input
            placeholder="Password"
            type="password"
            size="lg"
            focusBorderColor="teal.400"
          />

          <Button
            colorScheme="blue"
            size="lg"
            width="full"
            onClick={handleLogin}
          >
            {isLogin ? "Login" : "SignUp"}
          </Button>

          <Text fontSize="sm" color="gray.500">
            {isLogin ? "Don't have an account? " : "Already have an account? "}
            <Button
              variant="link"
              colorScheme="teal"
              onClick={() => setIsLogin(!isLogin)}
            >
              {isLogin ? "Signup" : "Login"}
            </Button>
          </Text>
        </VStack>
      </Box>
    </Box>
  );
}

export default Login;
