import { Navigate, Outlet, useLocation } from "react-router-dom";
import { getAuthContext } from "../context/ContextDemo";

// First, we create a component that will act as our security guard
function ProtectedRoute() {
  // We'll store authentication state in a variable
  // In a real app, this would come from your auth system (like Firebase, Auth0, etc.)
  const { isAuthenticated } = getAuthContext();

  // useLocation helps us remember where the user was trying to go
  const location = useLocation();

  // If user isn't authenticated, redirect to login
  // We save their intended destination so we can send them there after login
  if (!isAuthenticated) {
    return <Navigate to="/login" state={{ from: location }} replace />;
  }

  // If they are authenticated, render the protected content
  // Outlet renders any child routes
  return <Outlet />;
}

export default ProtectedRoute;
