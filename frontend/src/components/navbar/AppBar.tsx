import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import AirlinesIcon from "@mui/icons-material/Airlines";
import { Box } from "@mui/material";

export const Navbar = () => {
  return (
    <AppBar
      position="static"
      sx={{
        backgroundColor: "var(primary-color)",
        boxShadow: "none",
        padding: "0.5rem 1rem",
      }}
    >
      <Toolbar>
        <Box
          sx={{
            display: "flex",
            flexDirection: { xs: "column", sm: "row" },
            alignItems: "center",
            gap: 1,
            flexGrow: 1,
          }}
        >
          <Typography
            variant="h4"
            component="div"
            sx={{
              color: "var(--secondary-color)",
              fontWeight: 700,
              display: "flex",
              alignItems: "center",
              gap: 1,
            }}
          >
            Flight Offers
            <AirlinesIcon
              sx={{
                fontSize: "2.5rem",
                verticalAlign: "middle",
              }}
            />
          </Typography>
          <Typography
            variant="body2"
            component="span"
            sx={{
              color: "var(--secondary-color)",
              fontWeight: 500,
              fontSize: { xs: "0.7rem", sm: "0.8rem" },
              alignSelf: { xs: "center", sm: "flex-end" },
              mt: { xs: "-0.5rem", sm: "0" }
            }}
          >
            by Derek Ayala
          </Typography>
        </Box>
      </Toolbar>
    </AppBar>
  );
};
