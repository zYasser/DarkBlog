import React from "react";
import { NavBar } from "../../components/Navbar";
import { useRouter } from "next/router";

interface PostProps {}

export const Post: React.FC<PostProps> = ({}) => {
  const router = useRouter();
  const { id } = router.query;
  console.log(id);

  return (
    <div>
      <NavBar />
      <div className="w-4/5 items-center m-auto my-10 border-solid bg-white p-2 rounded-md shadow-xl">
        <div className="my-4 ">
          <h1 className="my-4">title</h1>
          <h2>by root</h2>
        </div>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Doloremque
          quas cum perferendis fugit adipisci, accusantium ad assumenda beatae,
          possimus delectus pariatur voluptas minus veniam provident accusamus
          repudiandae exercitationem magni voluptates? Vitae, blanditiis. Id
          similique nostrum assumenda nesciunt error nisi hic veniam ullam
          recusandae voluptatum, qui voluptatibus doloribus officia quaerat
          facilis tenetur atque rem saepe. Dolores ex architecto similique in
          exercitationem rerum saepe quod odio quaerat officia excepturi
          assumenda, sequi optio nihil harum at ea aspernatur dicta cum, hic
          facilis amet! Tempora perferendis tenetur at cum dolorem officiis
          veritatis sed commodi omnis praesentium, vel voluptas ex dignissimos
          debitis molestias ratione tempore quod! Aliquam temporibus saepe,
          consequatur dolores, possimus excepturi repellendus, ea vitae soluta
          delectus ad culpa sint voluptate necessitatibus illo distinctio nemo
          similique facere deserunt? Ipsam a minima rerum expedita ipsa odio
          culpa, incidunt non nisi accusantium recusandae laboriosam delectus,
          temporibus quisquam maiores doloribus quod facere beatae quae in.
          Nihil eius quo aut aspernatur facere non, eos exercitationem rerum
          aperiam tempora quia magnam id voluptate ipsum culpa quas soluta?
          Voluptatibus quisquam quis aliquam iure esse? Quae ipsum aliquid
          blanditiis reiciendis modi, laudantium, unde optio in ipsam corrupti
          consequuntur magni aperiam laborum laboriosam animi doloribus fugiat.
          Assumenda soluta tempora sed totam aliquid porro iure unde ipsa, animi
          rerum pariatur voluptatibus temporibus, itaque molestiae dolorem,
          nulla provident laudantium ab ducimus nemo consectetur? Quaerat
          doloribus ut beatae provident voluptatem unde, officia mollitia labore
          dignissimos. Nihil illo possimus hic doloremque doloribus sed tenetur
          ipsum! Consectetur maxime officiis quia quo expedita accusamus quaerat
          nesciunt fugit. Veritatis laboriosam accusantium voluptatum soluta ab
          optio cumque eos maxime. Voluptates corporis aspernatur, reiciendis
          veritatis accusantium eveniet libero vitae facere, consequuntur, ut
          itaque. Impedit voluptatem optio perferendis eos ullam amet nulla
          similique minus facere, dolore at tempore. Ad labore soluta velit
          quibusdam. Saepe, illum beatae. Amet velit obcaecati dolorum veritatis
          earum.
        </p>
      </div>
    </div>
  );
};
export default Post;
