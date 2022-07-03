import { Box, Container } from "@chakra-ui/react";
import react from "react";
import Title from "./title";

const Layout = ({
  title,
  children,
}: {
  title: string;
  children: react.ReactNode;
}) => {
  return (
    <>
      <Container maxW="80vw" my={5}>
        <Box my={5}>
          <Title title={title} />
        </Box>
        <Box>{children}</Box>
      </Container>
    </>
  );
};

export default Layout;
