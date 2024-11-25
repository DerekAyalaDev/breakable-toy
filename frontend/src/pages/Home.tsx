import { Container } from "@mui/material";
import { Navbar } from "../components/navbar/AppBar";
import { SearchBox } from "../components/search/SearchBox";
import { SearchProvider } from "../context/search/SearchContext";

export const Home = () => {
  return (
    <>
      <Navbar />
      <Container
        maxWidth="md"
        sx={{
          marginTop: "2rem",
          marginBottom: "4rem",
          display: "flex",
          flexDirection: "column",
          justifyContent: "flex-start",
        }}
      >
        <SearchProvider>
          <SearchBox />
        </SearchProvider>
      </Container>
    </>
  );
};

export default Home;
