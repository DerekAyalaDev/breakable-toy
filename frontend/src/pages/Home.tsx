import { Container } from "@mui/material";
import { Navbar } from "../components/navbar/AppBar";
import { SearchBox } from "../components/search/SearchBox";

export const Home = () => {
  return (
    <>
      <Navbar />
      <Container
        maxWidth="md"
        sx={{
          marginTop: "2rem",
        }}
      >
        <SearchBox />
      </Container>
    </>
  );
};

export default Home;
