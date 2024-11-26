import { Box, Typography, Divider } from '@mui/material';

interface PriceDetailsProps {
  base: string | undefined;
  fees: string | undefined;
  total: string | undefined;
  currency: string | undefined;
  perTraveler: string | undefined;
}

export const PriceDetails = ({
  base,
  fees,
  total,
  currency,
  perTraveler,
}: PriceDetailsProps) => {
  return (
    <Box
      sx={{
        flex: 1,
        border: '1px solid var(--secondary-color)',
        borderRadius: '8px',
        padding: '1rem',
        display: 'flex',
        flexDirection: 'column',
        gap: '1rem',
        backgroundColor: '#fff',
      }}
    >
      {/* Price Breakdown */}
      <Typography variant="h6" fontWeight={600} sx={{ color: 'var(--primary-color)' }}>
        Price Breakdown
      </Typography>
      <Box>
        <Typography variant="body1" fontWeight={500} color="text.secondary">
          Base: {base} {currency}
        </Typography>
        <Typography variant="body1" fontWeight={500} color="text.secondary">
          Fees: {fees} {currency}
        </Typography>
        <Typography variant="body1" fontWeight={500} color="text.secondary">
          Total: {total} {currency}
        </Typography>
      </Box>
      <Divider />

      {/* Price Per Traveler */}
      <Typography variant="h6" fontWeight={600} sx={{ color: 'var(--primary-color)' }}>
        Per Traveler
      </Typography>
      <Box>
        <Typography variant="body1" fontWeight={500} color="text.secondary">
          Total: {perTraveler} {currency}
        </Typography>
      </Box>
    </Box>
  );
};
