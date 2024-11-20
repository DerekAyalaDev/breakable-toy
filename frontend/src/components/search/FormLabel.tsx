import { Typography } from "@mui/material";

interface FormLabelProps {
  label: string;
}

export const FormLabel = ({ label }: FormLabelProps) => {
  return (
    <Typography
      variant="subtitle1"
      component="label"
      sx={{
        color: "var(--dark-secondary-color)",
        fontWeight: 600,
        flex: "1",
        minWidth: "80px",
        maxWidth: "130px",
        textAlign: "end",
      }}
    >
      {label}
    </Typography>
  );
};
