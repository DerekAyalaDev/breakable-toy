import { Box, Button } from "@mui/material";
import { PaginationProps } from "./types";

export const Pagination = ({ totalPages, currentPage, onPageChange }: PaginationProps) => {
  const getPageNumbers = () => {
    const maxButtons = 5;
    const pages = [];

    const start = Math.max(1, currentPage - Math.floor(maxButtons / 2));
    const end = Math.min(totalPages, start + maxButtons - 1);

    for (let i = start; i <= end; i++) {
      pages.push(i);
    }

    return pages;
  };

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        gap: "0.5rem",
        marginTop: "2rem",
      }}
    >
      {getPageNumbers().map((page) => (
        <Button
          key={page}
          onClick={() => onPageChange(page)}
          variant="contained"
          sx={{
            backgroundColor: page === currentPage ? "var(--dark-secondary-color)" : "var(--secondary-color)",
            color: "#FFFFFF",
            fontWeight: 600,
            minWidth: "40px",
            height: "40px",
            borderRadius: "15px",
            "&:hover": {
              backgroundColor: "var(--dark-secondary-color)",
            },
            transition: "background-color 0.3s ease",
          }}
        >
          {page}
        </Button>
      ))}
    </Box>
  );
};
