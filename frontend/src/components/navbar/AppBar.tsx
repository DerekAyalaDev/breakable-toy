import AppBar from "@mui/material/AppBar";
import Toolbar from "@mui/material/Toolbar";
import Typography from "@mui/material/Typography";
import AirlinesIcon from "@mui/icons-material/Airlines";

export const Navbar = () => {
  return (
    <AppBar
      position="static"
      sx={{
        backgroundColor: "var(--dark-primary-color)",
        boxShadow: "none",
        padding: "0.5rem 1rem",
      }}
    >
      <Toolbar>
        <Typography
          variant="h4"
          component="div"
          sx={{
            color: "var(--secondary-color)",
            fontWeight: 700,
            flexGrow: 1,
            display: "flex",
            alignItems: "center",
            gap: 1,
          }}
        >
          Flight Offers
          <AirlinesIcon
            sx={{
              fontSize: "2.5rem",
              verticalAlign: "middle"
            }} />{" "}
          <Typography
            variant="body2"
            component="span"
            sx={{
              color: "var(secondary-color)",
              fontWeight: 500,
              fontSize: "0.8rem",
              alignSelf: "flex-end"
            }}
          >
            by Derek Ayala
          </Typography>
        </Typography>
      </Toolbar>
    </AppBar>
  );
};
