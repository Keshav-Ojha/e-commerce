import Keycloak from "keycloak-js";

const keycloak = new Keycloak({
  url: "http://localhost:8080",
  realm: "e-comm",
  clientId: "ecomm-react-app",
});

export default keycloak;
