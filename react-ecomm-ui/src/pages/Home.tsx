import { Box, Button, Heading, HStack, Text, VStack } from "@chakra-ui/react";
import { useKeycloak } from "@react-keycloak/web";
import React from "react";
import { useNavigate } from "react-router-dom";

const HomePage: React.FC = () => {
  const navigate = useNavigate();
  const { keycloak } = useKeycloak();

  function handleLogin() {
    keycloak.login();
  }
  return (
    <Box className="min-h-screen bg-gradient-to-b from-gray-50 to-gray-200 flex flex-col">
      {/* Hero Section */}
      <Box className="flex flex-1 justify-center items-center text-center p-8">
        <VStack spacing={6}>
          <Heading
            size="2xl"
            color="blue.600"
            className="font-extrabold text-4xl md:text-5xl"
          >
            LuxeApparel
          </Heading>
          <Text className="text-lg md:text-xl text-gray-700 max-w-md">
            Redefining fashion with elegance and style. Explore premium clothing
            for every occasion.
          </Text>
          <HStack spacing={4}>
            <Button
              size="lg"
              variant="outline"
              colorScheme="blue"
              className="shadow-lg"
              onClick={() => handleLogin()}
            >
              Login
            </Button>
          </HStack>
        </VStack>
      </Box>

      {/* About Section */}
      <Box className="bg-white p-8 shadow-lg">
        <Box className="max-w-3xl mx-auto text-center">
          <Heading size="lg" className="text-gray-800 mb-4">
            About LuxeApparel
          </Heading>
          <Text className="text-gray-600 leading-relaxed">
            At LuxeApparel, we believe that fashion is an extension of your
            personality. Our curated collection offers a blend of modern
            sophistication and timeless designs. From casual wear to formal
            attire, find outfits that speak your style and make every moment
            memorable.
          </Text>
        </Box>
      </Box>
    </Box>
  );
};

export default HomePage;
