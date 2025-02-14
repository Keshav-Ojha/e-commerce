import { createRoot } from "react-dom/client";
import "./tailwind.css";
import App from "./App.tsx";
import { ChakraProvider } from "@chakra-ui/react";
import ContextDemo from "./context/ContextDemo.tsx";
import { ReactKeycloakProvider } from "@react-keycloak/web";
import keycloak from "./auth/keycloak.ts";

createRoot(document.getElementById("root")!).render(
  <ChakraProvider>
    <ContextDemo>
      <ReactKeycloakProvider authClient={keycloak}>
        <App />
      </ReactKeycloakProvider>
    </ContextDemo>
  </ChakraProvider>
);
